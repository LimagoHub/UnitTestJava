package de.bsm.euro2dollar;

import de.bsm.euro2dollar.de.gui.presenter.Euro2DollarPresenter;
import de.bsm.euro2dollar.de.gui.presenter.IEuro2DollarPresenter;
import de.bsm.euro2dollar.de.model.Euro2DollarRechnerImpl;
import de.bsm.euro2dollar.de.model.IEuro2DollarRechner;
import de.bsm.euro2dollar.gui.Euro2DollarRechnerView;
import de.bsm.euro2dollar.gui.IEuro2DollarRechnerView;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	IEuro2DollarRechner model = new Euro2DollarRechnerImpl();
		
		IEuro2DollarPresenter presenter = new Euro2DollarPresenter();
		
		IEuro2DollarRechnerView view = new Euro2DollarRechnerView();
		
		
		presenter.setView(view);
		presenter.setModel(model);
		
		view.setPresenter(presenter);
		
		presenter.populateItems();
		
		view.show();
    }
}
