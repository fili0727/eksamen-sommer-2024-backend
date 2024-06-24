package kea.eksamen.backend.disciplin;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.Optional;

import kea.eksamen.backend.enums.ResultatEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DisciplinController.class)
public class DisciplinControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinService disciplinService;

    @Test
    public void testFindAlleDiscipliner() throws Exception {
        Disciplin disciplin1 = new Disciplin("test", ResultatEnum.TID);
        Disciplin disciplin2 = new Disciplin("test2", ResultatEnum.POINT);

        when(disciplinService.findAlleDiscipliner()).thenReturn(Arrays.asList(disciplin1, disciplin2));

        mockMvc.perform(get("/api/discipliner"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].disciplinNavn").value("test"))
                .andExpect(jsonPath("$[0].resultatEnum").value("TID"))
                .andExpect(jsonPath("$[1].disciplinNavn").value("test2"))
                .andExpect(jsonPath("$[1].resultatEnum").value("POINT"));
    }

    @Test
    public void testFindDisciplinMedId() throws Exception {
        Disciplin disciplin = new Disciplin("test", ResultatEnum.TID);

        when(disciplinService.findDisciplinMedId(1)).thenReturn(Optional.of(disciplin));

        mockMvc.perform(get("/api/discipliner/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.disciplinNavn").value("test"))
                .andExpect(jsonPath("$.resultatEnum").value("TID"));
    }

    @Test
    public void testFindDisciplinMedId_NotFound() throws Exception {
        when(disciplinService.findDisciplinMedId(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/discipliner/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testOpretDisciplin() throws Exception {
        Disciplin disciplin = new Disciplin("test", ResultatEnum.TID);

        when(disciplinService.opretDisciplin(any(Disciplin.class))).thenReturn(disciplin);

        mockMvc.perform(post("/api/discipliner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"disciplinNavn\": \"test\", \"resultatEnum\": \"TID\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.disciplinNavn").value("test"))
                .andExpect(jsonPath("$.resultatEnum").value("TID"));
    }

    @Test
    public void testOpretDisciplin_InternalServerError() throws Exception {
        when(disciplinService.opretDisciplin(any(Disciplin.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/discipliner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"disciplinNavn\": \"test\", \"resultatEnum\": \"TID\"}"))
                .andExpect(status().isInternalServerError());
    }
}
