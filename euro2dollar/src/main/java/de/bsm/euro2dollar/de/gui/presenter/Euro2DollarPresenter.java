package de.bsm.euro2dollar.de.gui.presenter;

import de.bsm.euro2dollar.de.model.IEuro2DollarRechner;
import de.bsm.euro2dollar.gui.IEuro2DollarRechnerView;

public class Euro2DollarPresenter implements IEuro2DollarPresenter {
	
	private IEuro2DollarRechnerView view;
	private IEuro2DollarRechner model;
	
	
	
	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#getView()
	 */
	@Override
	public IEuro2DollarRechnerView getView() {
		return view;
	}

	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#setView(de.gui.IEuro2DollarRechnerView)
	 */
	@Override
	public void setView(IEuro2DollarRechnerView view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#getModel()
	 */
	@Override
	public IEuro2DollarRechner getModel() {
		return model;
	}

	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#setModel(de.model.IEuro2DollarRechner)
	 */
	@Override
	public void setModel(IEuro2DollarRechner model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#rechnen()
	 */
	@Override
	public void rechnen() {
	
		try {
			double euro = Double.parseDouble(view.getEuro());
			double dollar = model.calculateEuro2Dollar(euro);
			view.setDollar(Double.toString(dollar));
		} catch (NullPointerException | NumberFormatException e) {
			view.setDollar("Keine Zahl");
		} catch (RuntimeException e) {
			view.setDollar("interner Serverfehler");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#beenden()
	 */
	@Override
	public void beenden() {
		view.close();
	}
	
	/* (non-Javadoc)
	 * @see de.gui.presenter.IEuro2DollarPresenter#populateItems()
	 */
	@Override
	public void populateItems() {
		view.setEuro("0.0");
		view.setDollar("0.0");
		view.setRechnenEnabled(true);
	}

	@Override
	public void updateRechnenActionState() {
		
		try {
			Double.parseDouble(view.getEuro());
			view.setRechnenEnabled(true);
		} catch (NumberFormatException e) {
			view.setRechnenEnabled(false);
		}
	}

}
