package de.bsm.euro2dollar.de.gui.presenter;

import de.bsm.euro2dollar.de.model.IEuro2DollarRechner;
import de.bsm.euro2dollar.gui.IEuro2DollarRechnerView;

public interface IEuro2DollarPresenter {

	public abstract IEuro2DollarRechnerView getView();

	public abstract void setView(IEuro2DollarRechnerView view);

	public abstract IEuro2DollarRechner getModel();

	public abstract void setModel(IEuro2DollarRechner model);

	public abstract void rechnen();

	public abstract void beenden();

	public abstract void populateItems();
	
	public abstract void updateRechnenActionState();

}