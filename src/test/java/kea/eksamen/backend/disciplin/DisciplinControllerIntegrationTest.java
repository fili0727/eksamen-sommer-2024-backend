package kea.eksamen.backend.disciplin;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class DisciplinControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DisciplinService disciplinService;

    @Test
    public void testFindAlleDiscipliner() throws Exception {
        Disciplin disciplin = new Disciplin();
        // Set properties for disciplin object

        given(disciplinService.findAlleDiscipliner()).willReturn(Collections.singletonList(disciplin));

        mockMvc.perform(get("/api/discipliner"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(disciplin.getId()));
    }

    @Test
    public void testFindDisciplinMedId() throws Exception {
        Disciplin disciplin = new Disciplin();
        // Set properties for disciplin object
        int id = 1; // replace with actual id

        given(disciplinService.findDisciplinMedId(id)).willReturn(Optional.of(disciplin));

        mockMvc.perform(get("/api/discipliner/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(disciplin.getId()));
    }

    @Test
    public void testOpretDisciplin() throws Exception {
        Disciplin disciplin = new Disciplin();
        // Set properties for disciplin object

        given(disciplinService.opretDisciplin(any(Disciplin.class))).willReturn(disciplin);

        mockMvc.perform(post("/api/discipliner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(disciplin)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(disciplin.getId()));
    }

    @Test
    public void testSletDisciplin() throws Exception {
        int id = 1; // replace with actual id

        doNothing().when(disciplinService).sletDisciplin(id);

        mockMvc.perform(delete("/api/discipliner/" + id))
                .andExpect(status().isNoContent());
    }
}