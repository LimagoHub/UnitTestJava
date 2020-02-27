package de.math;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MultipliziererOptimiererTest {
	
	@InjectMocks private MultipliziererOptimierer objectUnderTest;
	@Mock private Multiplizierer multipliziererMock;
	
//	@Before
//	public void setUp() {
//		multipliziererMock = Mockito.mock(Multiplizierer.class);
//		objectUnderTest = new MultipliziererOptimierer(multipliziererMock);
//	}
	
	@Test
	public void test() {
		when(multipliziererMock.times(anyInt(), anyInt())).thenReturn(50L);
		
		
		// Action
		objectUnderTest.times(1, 1000);
		
		
		verify(multipliziererMock, times(1)).times(1, 1000);
	}

	@Test
	public void test2() {
		when(multipliziererMock.times(anyInt(), anyInt())).thenReturn(50L);
		
		
		// Action
		objectUnderTest.times(1000, 1);
		
		
		verify(multipliziererMock).times(1, 1000);
	}


}
