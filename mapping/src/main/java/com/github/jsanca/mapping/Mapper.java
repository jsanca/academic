package com.github.jsanca.mapping;

import com.google.common.base.CaseFormat;
import org.apache.commons.collections.keyvalue.MultiKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Mapper {

    private final Map<MultiKey, Mapping> classFieldMappingMap =
            new ConcurrentHashMap<>();

    private final Map<Class<FieldMapper>, FieldMapper> customClassFieldMapperInstancesMap =
            new ConcurrentHashMap<>();


    private volatile List<CreatorAndSetterStrategy> creatorAndSetterStrategies =
            new ArrayList<>();

    private final CreatorAndSetterStrategy DEFAULT_CREATOR_AND_SETTER_STRATEGY =
            new SimpleBeanSetterStrategy();

    private static final FieldNameMapperStrategy DEFAULT_FIELD_NAME_MAPPER_STRATEGY =
            new LowerUnderscoreFieldNameMapperStrategy();

    private static final SimpleDateFormat DEFAULT_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");

    private final Map<String, SimpleDateFormat> dateFormatMap =
            new ConcurrentHashMap<>();

    private final Map<Class, FieldMapper> classFieldMapperMap =
                    new ConcurrentHashMap<>();

    private static final  FieldMapper DEFAULT_FIELD_MAPPER = (input, mapping) -> input;

    {
        this.addMapper(Integer.TYPE, this::parseInt);
        this.addMapper(Boolean.TYPE, this::parseBoolean);
        this.addMapper(Date.class,   this::parseDate);
        this.addMapper(String.class, this::parseString);

        this.addCreatorAndSetterStrategy(new CreatorConstructorSetterStrategy());
        this.addCreatorAndSetterStrategy(new BuilderSetterStrategy());
    }

    private Date parseDate(final Object input, final Mapping mapping) {

        Date date = null;
        final SimpleDateFormat simpleDateFormat =
                 (null != mapping && !"".equals(mapping.dateFormat()))?
                         this.getDefaultDateFormat(mapping.dateFormat()):
                         DEFAULT_DATE_FORMAT;

        try {

            date = simpleDateFormat.parse(input.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            // todo: do something
        }

        return date;
    }

    private SimpleDateFormat getDefaultDateFormat (final String format) {

        if (!this.dateFormatMap.containsKey(format)) {

            this.dateFormatMap.put(format, new SimpleDateFormat(format));
        }

        return this.dateFormatMap.get(format);
    }

    private Integer parseInt(final Object input, final Mapping mapping) {
        return Integer.parseInt(input.toString());
    }

    private Boolean parseBoolean(final Object input, final Mapping mapping) {
        return Boolean.parseBoolean (input.toString());
    }

    private String parseString(final Object input, final Mapping mapping) {
        return input.toString();
    }

    public <T> void addMapper (final Class<T> classToUse,
                               final FieldMapper<T> fieldMapper) {

        this.classFieldMapperMap.put(classToUse, fieldMapper);
    }

    public synchronized void addCreatorAndSetterStrategy (final CreatorAndSetterStrategy creatorAndSetterStrategy) {

        this.creatorAndSetterStrategies.add(creatorAndSetterStrategy);
    }

    public <T> T toObject(final Map<String,String> map,
                          final Class<T> classToUse) {
        return this.toObject(map, classToUse, DEFAULT_FIELD_NAME_MAPPER_STRATEGY);
    }

    public <T> T toObject(final Map<String,String> inputMap,
                          final Class<T> classToUse,
                          final FieldNameMapperStrategy fieldNameMapperStrategy) {

        T object                = null;
        String columnName       = null;
        FieldMapper fieldMapper = null;
        Object      value       = null;
        Mapping     mapping     = null;
        final Field []       fields;
        final Map<String, Object> objectMap = new HashMap<>();

        try {

            fields = classToUse.getDeclaredFields(); // todo: get super fields.

            for (final Field field : fields) {

                mapping    = this.getMapping(classToUse, field);
                columnName = this.getColumnName
                        (classToUse, field, mapping, fieldNameMapperStrategy);
                if (null != columnName && inputMap.containsKey(columnName)) {

                    fieldMapper = this.getFieldMapper(classToUse, field, mapping);
                    if (null != fieldMapper) {

                        value = fieldMapper.toObject(inputMap.get(columnName), mapping);
                        objectMap.put(field.getName(), value);
                    } else {
                        // todo: warn field does not have fieldMapper
                    }
                } else {
                    // todo: warn field/column does not have input
                }
            }

            object = this.createObject(classToUse, objectMap);
        } catch (Exception e) {
            e.printStackTrace();
            // todo: do something
        }

        return object;
    } // toObject.

    private <T> T createObject (final Class<T> classToUse,
                                final Map<String, Object> objectMap)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        for (CreatorAndSetterStrategy strategy : this.creatorAndSetterStrategies) {

            if (strategy.canApply(classToUse)) {

                return strategy.createAndSet(classToUse, objectMap);
            }
        }

        return DEFAULT_CREATOR_AND_SETTER_STRATEGY.createAndSet(classToUse, objectMap);
    }


    private <T> FieldMapper getFieldMapper(final Class<T> classToUse,
                                           final Field field,
                                           final Mapping mapping) throws InstantiationException, IllegalAccessException {

        final FieldMapper fieldMapper =
                (null != mapping && !FieldMapper.class.equals(mapping.fieldMapper()))?
                    this.createFieldMapper (mapping):
                    this.findFieldMapper   (field);

        return fieldMapper;
    }

    private FieldMapper findFieldMapper(final Field field) {

        final Class<?> aClass         = field.getType();
        final FieldMapper fieldMapper =
                (this.classFieldMapperMap.containsKey(aClass))?
                        this.classFieldMapperMap.get(aClass):
                        DEFAULT_FIELD_MAPPER;

        return fieldMapper;
    }

    private FieldMapper createFieldMapper(final Mapping mapping) throws IllegalAccessException, InstantiationException {

        if (!this.customClassFieldMapperInstancesMap.containsKey(mapping.fieldMapper())) {

            final FieldMapper fieldMapper =
                    mapping.fieldMapper().newInstance();

            this.customClassFieldMapperInstancesMap.put
                    (mapping.fieldMapper(), fieldMapper);
            return fieldMapper;
        }

        return this.customClassFieldMapperInstancesMap.get(mapping.fieldMapper());
    }

    private <T> String getColumnName(final Class<T> classToUse,
                                     final Field field,
                                     final Mapping mapping,
                                     final FieldNameMapperStrategy fieldNameMapperStrategy) {

        final String columnName =
                (null != mapping && !"".equals(mapping.column()))?
                        mapping.column(): fieldNameMapperStrategy.handle(field.getName());

        return columnName;
    }

    private Mapping getMapping (final Class classToUse,
                                final Field field) {

        final MultiKey multiKey = new MultiKey(classToUse, field);

        if (!this.classFieldMappingMap.containsKey(multiKey)) {

            final Mapping mapping = (Mapping) AnnotationUtils.
                    getAttributeAnnotation(field, Mapping.class);
            this.classFieldMappingMap.put(multiKey, mapping);
            return mapping;
        }

        return this.classFieldMappingMap.get(multiKey);
    }

    private final static class LowerUnderscoreFieldNameMapperStrategy implements FieldNameMapperStrategy {

        public String handle(final String name) {
            return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
        }
    }
}
