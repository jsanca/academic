package designpatterns2.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import designpatterns2.dao.StudentDAO;
import designpatterns2.vo.dto.StudentDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Json student
 * @author jsanca
 */
public class JsonStudentDAOImpl implements StudentDAO {

    private ObjectMapper mapperJson = new ObjectMapper();

    private String identifierListFileLocation = null;
    private String studentFilesDirectoryLocation   = null;

    @Override
    public List<StudentDTO> getAll() {

        Reader reader = null;
        List<String> idList = null;
        List<StudentDTO> studentDTOList = null;

        try {

            reader = new FileReader(this.identifierListFileLocation);
            idList =
                    Arrays.asList(this.mapperJson.readValue(reader, String[].class));

        } catch (Exception e) {

            // do something
        }

        return studentDTOList;
    }

    @Override
    public StudentDTO searchByNumber(String number) {

        Reader reader = null;
        StudentDTO studentDTO = null;

        try {

            reader = new FileReader(new File(this.identifierListFileLocation,
                    new StringBuilder(number).append(".json").toString()));
            studentDTO =
                    this.mapperJson.readValue(reader, StudentDTO.class);

        } catch (Exception e) {

            // do something
        }

        return studentDTO;
    }

    @Override
    public boolean remove(String number) {

        File file = null;
        boolean remove = false;

        file = new File(this.identifierListFileLocation,
                new StringBuilder(number).append(".json").toString());

        if (file.exists()) {

            remove = file.delete();
        }

        return remove;
    }

    @Override
    public boolean update(StudentDTO studentDTO) {

        // todo:
        return false;
    }

    @Override
    public boolean create(StudentDTO studentDTO) {

        // todo
        return false;
    }
} // E:O:F:JsonStudentDAOImpl.
