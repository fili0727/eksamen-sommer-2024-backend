package kea.eksamen.backend.deltager;


import com.fasterxml.jackson.databind.ObjectMapper;
import kea.eksamen.backend.enums.Klub;
import kea.eksamen.backend.enums.Køn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static kea.eksamen.backend.enums.Klub.DEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class DeltagerControllerIntegrationTest {
//LAVET AF CO PILOT
    @Autowired
    private MockMvc mockMvc;

    private Deltager testDeltager;
    private String testDeltagerJson;

    @BeforeEach
    public void setup() throws Exception {

        testDeltager = new Deltager();

        testDeltager.setNavn("Test Navn");
        testDeltager.setAlder(20);
        testDeltager.setId(1);
        testDeltager.setKlub(Klub.DEN);
        testDeltager.setKøn(Køn.MAND);

        // Convert the testDeltager object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        testDeltagerJson = objectMapper.writeValueAsString(testDeltager);
    }

    @Test
    public void testFindAlleDeltagere() throws Exception {
        mockMvc.perform(get("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].navn", is("Test Navn")))
                .andExpect(jsonPath("$[0].alder", is(20)))
                .andExpect(jsonPath("$[0].klub", is("DEN")))
                .andExpect(jsonPath("$[0].køn", is("MAND")));
    }

    @Test
    public void testFindDeltagerMedId() throws Exception {
        int id = 1;
        mockMvc.perform(get("/api/deltagere/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.navn", is("Test Navn")))
                .andExpect(jsonPath("$.alder", is(20)))
                .andExpect(jsonPath("$.klub", is("DEN")))
                .andExpect(jsonPath("$.køn", is("MAND")));
    }

    @Test
    public void testOpretDeltager() throws Exception {
        mockMvc.perform(post("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testDeltagerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.navn", is("Test Navn")))
                .andExpect(jsonPath("$.alder", is(20)))
                .andExpect(jsonPath("$.klub", is("DEN")))
                .andExpect(jsonPath("$.køn", is("MAND")));
    }


}
