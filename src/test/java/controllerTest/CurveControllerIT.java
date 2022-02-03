package controllerTest;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.impl.CurvePointService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurveController.class)
public class CurveControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private CurvePointService curvePointService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenHomeThenReturnViewcurvePointAndAllcurvePoint() throws Exception {
        CurvePoint curvePoint = new CurvePoint(1
                , 2D
                , 3D);
        List<CurvePoint> curveList = Arrays.asList(curvePoint);
        when(curvePointService.getAllModels()).thenReturn(curveList);

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("allCurvePoint"))
                .andReturn();
    }

    @Test
    public void whenAddCurveFormThenReturnViewCurvePointAdd() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(view().name("curvePoint/add"))
                .andExpect(model().attributeExists("curvePoint"))
                .andReturn();
    }

    @Test
    public void whenValidateThenReturnCurvePoint() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10D);
        curvePoint.setValue(1D);
        when(curvePointService.saveModel(curvePoint)).thenReturn(curvePoint);
        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "1")
                        .param("term", "10")
                        .param("value", "1"))
                .andExpect(view().name("redirect:/curvePoint/list"));
        verify(curvePointService).saveModel(curvePoint);
    }

    @Test
    public void whenGetShowUpdateFormThenReturnFormAndModel() throws Exception {
        CurvePoint curvePoint = new CurvePoint(1
                , 10D
                , 1D);
        when(curvePointService.getModel(1)).thenReturn(curvePoint);

        mockMvc.perform(get("/curvePoint/update/{id}", 1))
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/update"));
        verify(curvePointService).getModel(1);
    }

    @Test
    public void whenPostUpdateBidThenReturncurvePoint() throws Exception {
        CurvePoint curvePoint = new CurvePoint(1
                , 10D
                , 5D);
        curvePoint.setId(1);

        mockMvc.perform(post("/curvePoint/update/{id}", "1")
                        .param("curveId", "1")
                        .param("term", "10")
                        .param("value", "5"))
                .andExpect(view().name("redirect:/curvePoint/list"));
        verify(curvePointService).updateModel(1, curvePoint);
    }

    @Test
    public void whenGetDeleteBidThenReturncurvePoint() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/{id}", "1"))
                .andExpect(view().name("redirect:/curvePoint/list"));
        verify(curvePointService).deleteModel(1);
    }
}