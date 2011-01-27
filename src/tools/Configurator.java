package tools;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Sound;


public class Configurator implements ButtonListener {
	
	private Configurable instance;
	private String[] settings;
	private int currentSetting;
	private boolean adjustSetting;
	private boolean listen = false;
	
	public Configurator(Configurable instance, String[] settings) {
		this.instance = instance;
		this.settings = settings;
		currentSetting = 0;
		adjustSetting = false;
		refreshSetting();
	}
	
	public void listen() {
		listen = true;
		Button.ENTER.addButtonListener(this);
		Button.LEFT.addButtonListener(this);
		Button.RIGHT.addButtonListener(this);
	}
	
	public void unlisten() {
		listen = false;
	}
	
	public void refreshSetting() {
		if(adjustSetting)
			LineDisplayWriter.writeLine(settings[currentSetting]+":["+instance.getSettingValue(currentSetting)+"]", 7);
		else
			LineDisplayWriter.writeLine(settings[currentSetting]+": "+instance.getSettingValue(currentSetting), 7);
	}
	
	public void selectNextSetting() {
		currentSetting++;
		if(currentSetting >= settings.length)
			currentSetting = 0;
		refreshSetting();
	}
	
	public void selectPreviousSetting() {
		currentSetting--;
		if(currentSetting < 0)
			currentSetting = settings.length - 1;
		refreshSetting();
	}
	
	@Override
	public void buttonPressed(Button button) {
		if(!listen)
			return;
		int steps = 0;
		while(button.isPressed()) {
			switch(button.getId()) {
			case Button.ID_LEFT:
				if(adjustSetting) {
					instance.decreaseSetting(currentSetting);
					refreshSetting();
				} else {
					selectPreviousSetting();
					return;
				}
				break;
			case Button.ID_RIGHT:
				if(adjustSetting) {
					instance.increaseSetting(currentSetting);
					refreshSetting();
				} else {
					selectNextSetting();
					return;
				}
				break;
			case Button.ID_ENTER:
				adjustSetting = !adjustSetting;
				refreshSetting();
				return;
			}
			try {
				if(steps == 0)
					Thread.sleep(300);
				else {
					Sound.playTone(Button.getKeyClickTone(button.getId()), Button.getKeyClickLength(), Button.getKeyClickVolume());
					if(steps < 10)
						Thread.sleep(100);
					else if(steps < 30)
						Thread.sleep(50);
					else
						Thread.sleep(20);
				}
				if(steps < 30)
					steps++;
			} catch (InterruptedException e) {}
		}
	}
	
	@Override
	public void buttonReleased(Button button) {
	}
	
}
