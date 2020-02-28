package de.bsm.euro2dollar.gui;

import de.bsm.euro2dollar.de.gui.presenter.IEuro2DollarPresenter;

public interface IEuro2DollarRechnerView {

	public IEuro2DollarPresenter getPresenter();



	public void setPresenter(IEuro2DollarPresenter presenter);

	public abstract void show();
	
	public abstract void close();

	public abstract String getEuro();

	public abstract void setEuro(String euro);

	public abstract String getDollar();

	public abstract void setDollar(String dollar);
	
	public abstract boolean isRechnenEnabled();

	public abstract void setRechnenEnabled(boolean enabled);


}