package com.data.generator.service;

import com.data.generator.entity.DataTable;
import com.github.javafaker.Faker;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import org.springframework.web.server.ResponseStatusException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    public static final String FILE_NAME = "GeneratedData.csv ";

    public String generateData(Long rows) {
        List<DataTable> generatedList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            Faker faker = new Faker();
            DataTable generated = new DataTable();
            generated.setName(faker.name().name());
            generated.setPhone(faker.phoneNumber().cellPhone());
            generated.setEmail(faker.internet().emailAddress());
            generatedList.add(generated);
        }
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.append("Name,Phone,Email");
            writer.append("\n");
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(DataTable.class);

            // Arrange column name as provided in below array.
            String[] columns = new String[]{"name", "phone", "email"};
            mappingStrategy.setColumnMapping(columns);

            // Creating StatefulBeanToCsv object
            StatefulBeanToCsvBuilder<DataTable> builder = new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter = builder.withMappingStrategy(mappingStrategy).build();

            // Write list to StatefulBeanToCsv object
            beanWriter.write(generatedList);
            writer.close();
        } catch (Exception e) {
            String message = "Some issues with generating data..!!";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        return "Data generated successfully, please check database";
    }
}
