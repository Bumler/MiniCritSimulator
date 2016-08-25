package rollSim;

import java.util.ArrayList;

public class dice {
	ArrayList<die> DiceList = new ArrayList<die>();
	int explode;

	// generates 'inp' number of dice and holds them in a list
	public dice(int inp, int explode) {
		this.explode = explode;

		for (int i = 0; i < inp; i++) {
			DiceList.add(new die());
		}
	}

	public int totalDamage() {
		int total = 0;
		if (explode == 1) {
			for (die Die : DiceList) {
				total += Die.getDamageExplode();
			}
		}
		else if (explode == 2){
			for (die Die : DiceList) {
				total += Die.getDamageDoubleExplode();
			}
		}
		else{
			for (die Die : DiceList) {
				total += Die.getDamage();
			}
		}
		return total;
	}
}
