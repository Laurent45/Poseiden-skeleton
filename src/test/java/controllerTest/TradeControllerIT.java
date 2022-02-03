package controllerTest;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.impl.TradeService;
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
@SpringBootTest(classes = TradeController.class)
public class TradeControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private TradeService tradeService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenHomeThenReturnViewTradeAndAllTrade() throws Exception {
        Trade trade = new Trade(1
                , "account1"
                , "type1"
                , 2D);
        List<Trade> trades = Arrays.asList(trade);
        when(tradeService.getAllModels()).thenReturn(trades);

        mockMvc.perform(get("/trade/list"))
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("allTrade"))
                .andReturn();
        verify(tradeService).getAllModels();
    }

    @Test
    public void whenAddTradeFormThenReturnViewTradeAdd() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(view().name("trade/add"))
                .andExpect(model().attributeExists("trade"));
    }

    @Test
    public void whenValidateThenReturnTrade() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("account1");
        trade.setType("type1");
        trade.setBuyQuantity(2D);
        when(tradeService.saveModel(trade)).thenReturn(trade);

        mockMvc.perform(post("/trade/validate")
                        .param("account", "account1")
                        .param("type", "type1")
                        .param("buyQuantity", "2"))
                .andExpect(view().name("redirect:/trade/list"));
        verify(tradeService).saveModel(trade);
    }

    @Test
    public void whenGetShowUpdateFormThenReturnFormAndModel() throws Exception {
        Trade trade = new Trade(1
                , "account1"
                , "type1"
                , 2D);
        when(tradeService.getModel(1)).thenReturn(trade);

        mockMvc.perform(get("/trade/update/{id}", 1))
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeExists("trade"))
                .andExpect(view().name("trade/update"));
        verify(tradeService).getModel(1);
    }

    @Test
    public void whenPostUpdateBidThenReturnTrade() throws Exception {
        Trade trade = new Trade(1
                , "account1"
                , "type1"
                , 5D);

        mockMvc.perform(post("/trade/update/{id}", "1")
                        .param("account", "account1")
                        .param("type", "type1")
                        .param("buyQuantity", "5"))
                .andExpect(view().name("redirect:/trade/list"));
        verify(tradeService).updateModel(1, trade);
    }

    @Test
    public void whenGetDeleteBidThenReturnTrade() throws Exception {
        mockMvc.perform(get("/trade/delete/{id}", "1"))
                .andExpect(view().name("redirect:/trade/list"));
        verify(tradeService).deleteModel(1);
    }
}