package de.bsm.euro2dollar.de.gui.presenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import de.bsm.euro2dollar.de.model.IEuro2DollarRechner;
import de.bsm.euro2dollar.gui.IEuro2DollarRechnerView;

@RunWith(MockitoJUnitRunner.class)
public class Euro2DollarPresenterTest {
	@Mock
	private IEuro2DollarRechner modelMock;

	@Mock
	private IEuro2DollarRechnerView viewMock;
	@InjectMocks
	private Euro2DollarPresenter underTest;
	
	
	
	@Test
	public void beenden_ProperUse_success() {
				
		underTest.beenden();
		verify(viewMock).close();
	}
	
	@Test
	public void populateItems_ProperUse_success() {
			
		underTest.populateItems();
		verify(viewMock).setEuro("0.0");
		verify(viewMock).setDollar("0.0");
		verify(viewMock).setRechnenEnabled(true);
	}
	
	@Test
	public void rechnen_EuroValueNAN_errorMessageInDollarField() {
		
		when(viewMock.getEuro()).thenReturn("Herbert");
		underTest.rechnen();
		verify(viewMock).setDollar("Keine Zahl");
	}
	
	
	@Test
	public void rechnen_EuroValueNull_errorMessageInDollarField() {
		
		when(viewMock.getEuro()).thenReturn(null);
		underTest.rechnen();
		verify(viewMock).setDollar("Keine Zahl");
	}
	
	
	@Test
	public void updateRechnenActionState_EuroValueNAN_disableRechnenAction() {
		
		when(viewMock.getEuro()).thenReturn("Herbert");
		underTest.updateRechnenActionState();
		verify(viewMock).setRechnenEnabled(false);
		
	}
	
	@Test
	public void updateRechnenActionState_EuroValueIsNumeric_enableRechnenAction() {
		
		when(viewMock.getEuro()).thenReturn("10.0");
		underTest.updateRechnenActionState();
		verify(viewMock).setRechnenEnabled(true);
		
	}
	
	
	@Test
	public void rechnen_RuntimeExceptionInService_errorMessageInDollarField() {
		
		when(viewMock.getEuro()).thenReturn("10.0");
		when(modelMock.calculateEuro2Dollar(anyDouble())).thenThrow(new RuntimeException());
		
		underTest.rechnen();
		
		verify(viewMock).setDollar("interner Serverfehler");
	}
	

	
	@Test
	public void rechnen_ProperUse_ResultInDollarField() {
		
		when(viewMock.getEuro()).thenReturn("10.0");
		when(modelMock.calculateEuro2Dollar(anyDouble())).thenReturn(100.0);

		underTest.rechnen();
		verify(modelMock).calculateEuro2Dollar(10);
		verify(viewMock).setDollar("100.0");
		
	}
	


	
	
}
