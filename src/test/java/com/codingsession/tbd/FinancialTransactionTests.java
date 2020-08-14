package com.codingsession.tbd;


import com.codingsession.tbd.api.model.Amount;
import com.codingsession.tbd.api.model.FinancialTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:persistence-generic-entity.properties")
public class FinancialTransactionTests {
    private static final String CREATE_TESTDATA = "testdata.sql";
    public static final int SENDER = 1234;
    public static final int RECEIVER = 9999;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    public void before() throws SQLException {
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), new ClassPathResource(CREATE_TESTDATA));
    }


    @Test
    public void transactionCreated() throws Exception {
        FinancialTransaction financialTransaction = new FinancialTransaction();

        financialTransaction
                .sender(SENDER)
                .receiver(RECEIVER)
                .amount(createAmount(500))
                .nonce(UUID.randomUUID());


        mvc.perform(post("/sendMoney")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(financialTransaction)))
                .andExpect(status().isCreated());
    }

    private Amount createAmount(int value) {
        Amount amount = new Amount();
        amount.currency(Amount.CurrencyEnum.EUR).value(value);
        return amount;
    }


    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
