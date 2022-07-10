package com.txservices.swissbackend.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.txservices.swissbackend.dto.ConnectionDTO;
import com.txservices.swissbackend.entity.Transportation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ConnectionControllerTest {

    @Autowired
    MockMvc mockMvc;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    @Test
    public void testPostCall() throws Exception {
        String url = "http://localhost:8080/api_v1/connections";

        ConnectionDTO connectionDTO1 = new ConnectionDTO();
        connectionDTO1.setLocationFrom("Lausanne");
        connectionDTO1.setLocationTo("Genève");
        connectionDTO1.setTransportation(Transportation.TRAIN);
        connectionDTO1.setVia(null); //optional field

        ConnectionDTO connectionDTO2 = new ConnectionDTO();
        connectionDTO2.setLocationFrom(null);
        connectionDTO2.setLocationTo("Genève");
        connectionDTO2.setTransportation(Transportation.TRAIN);


        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(connectionDTO1);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());

        requestJson = ow.writeValueAsString(connectionDTO2);
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

}
