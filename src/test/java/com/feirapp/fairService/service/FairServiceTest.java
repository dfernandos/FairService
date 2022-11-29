package com.feirapp.fairService.service;

import com.feirapp.fairService.entity.Fair;
import com.feirapp.fairService.exceptions.FairException;
import com.feirapp.fairService.repository.FairRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

class FairServiceTest {

    @Mock
    private FairRepository fairRepository;

    private AutoCloseable autoCloseable;
    private FairService fairService;
    private Fair fair;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        fairService = new FairService(fairRepository);
        fair = new Fair("test", "test", "test", "test", 32.53, 43.43);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveFair() {
        //Verify if the repository is called when I use the SaveFair Method
        //also checks if the save method is called with the correct argument

        fairService.saveFair(fair);
        ArgumentCaptor<Fair> fairArgumentCaptor = ArgumentCaptor.forClass(Fair.class);
        verify(fairRepository).save(fairArgumentCaptor.capture());

        Fair captureFair = fairArgumentCaptor.getValue();

        assertThat(captureFair).isEqualTo(fair);
    }

    @Test
    void deleteFair() throws FairException {
        fairService.deleteFair(1);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(fairRepository).deleteById(argumentCaptor.capture());

        int capturedArgument = argumentCaptor.getValue();
        assertThat(capturedArgument).isEqualTo(1);
    }

    @Test
    void getFairByWeekDay() {
        fairService.getFairByWeekDay("test");
        verify(fairRepository).getByweekday("test");

        ArgumentCaptor<String> weekDayArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(fairRepository).getByweekday(weekDayArgumentCaptor.capture());

        String captorValue = weekDayArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo("test");
    }

    @Test
    void getFair() {
        fairService.getFair(1);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(fairRepository).findById(argumentCaptor.capture());

        int capturedArgument = argumentCaptor.getValue();
        assertThat(capturedArgument).isEqualTo(1);
    }

    @Test
    void getAllFairs() {
        fairService.getFairs();
        verify(fairRepository).findAll();

    }
}