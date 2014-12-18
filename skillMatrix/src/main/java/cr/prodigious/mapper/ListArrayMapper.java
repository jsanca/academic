package cr.prodigious.mapper;


import cr.prodigious.dto.cases.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListArrayMapper {

    public <B extends Serializable, E extends Entity> List<B> map
            (final E [] entityArray, final Mapper<E, B> mapper) {

        final List<B> beanList = new ArrayList<B>();

        for (E entity : entityArray) {

            beanList.add(mapper.map(entity));
        }

        return beanList;
    } // map.

    public <B extends Serializable, E extends Entity> E [] map
            (final List<B> beanList, final E [] entityArray, final Mapper<E, B> mapper) {

        int i = 0;
        if (beanList.size() == entityArray.length) {

            for (B bean : beanList) {

                entityArray[i++] = mapper.map(bean);
            }
        }

        return entityArray;
    } // map.



}
