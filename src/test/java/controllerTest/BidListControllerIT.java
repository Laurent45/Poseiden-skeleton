package controllerTest;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.impl.BidListService;
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
@SpringBootTest(classes = BidListController.class)
public class BidListControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private BidListService bidListService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenHomeThenReturnViewBidListAndAllBidList() throws Exception {
        BidList bidList = new BidList(1
                , "account1"
                , "type1"
                , 2D);
        List<BidList> bidLists = Arrays.asList(bidList);
        when(bidListService.getAllModels()).thenReturn(bidLists);

        mockMvc.perform(get("/bidList/list"))
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("allBids"));
    }

    @Test
    public void whenAddBidFormThenReturnViewBidListAdd() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(view().name("bidList/add"))
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    public void whenValidateThenReturnBidList() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("account1");
        bidList.setType("type1");
        bidList.setBidQuantity(5D);
        when(bidListService.saveModel(bidList)).thenReturn(bidList);
        mockMvc.perform(post("/bidList/validate")
                        .param("account", "account1")
                        .param("type", "type1")
                        .param("bidQuantity", "5.0"))
                .andExpect(view().name("redirect:/bidList/list"));
        verify(bidListService).saveModel(bidList);
    }

    @Test
    public void whenGetShowUpdateFormThenReturnFormAndModel() throws Exception {
        BidList bidList = new BidList(1
                , "account1"
                , "type1"
                , 5D);
        when(bidListService.getModel(1)).thenReturn(bidList);

        mockMvc.perform(get("/bidList/update/{id}", 1))
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"))
                .andExpect(view().name("bidList/update"));
        verify(bidListService).getModel(1);
    }

    @Test
    public void whenPostUpdateBidThenReturnBidList() throws Exception {
        BidList bidList = new BidList(1
                , "account1"
                , "type1"
                , 5D);

        mockMvc.perform(post("/bidList/update/{id}", "1")
                        .param("account", "account1")
                        .param("type", "type1")
                        .param("bidQuantity", "5"))
                .andExpect(view().name("redirect:/bidList/list"));
        verify(bidListService).updateModel(1, bidList);
    }

    @Test
    public void whenGetDeleteBidThenReturnBidList() throws Exception {
        mockMvc.perform(get("/bidList/delete/{id}", "1"))
                .andExpect(view().name("redirect:/bidList/list"));
        verify(bidListService).deleteModel(1);
    }
}
