package program;

import java.util.List;
import java.util.Scanner;

public class RumpushoppiKokeiluOhjelma {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Tuotesovellus");
		int valinta = 1;
		do {
			System.out
					.print("\n 1 Listaa tuotteet \n 2 Lisää tuote \n 3 Poista tuote \n 0 Lopeta");
			System.out.print("\n Syötä valintasi: ");
			valinta = input.nextInt();input.nextLine(); // syöttöpuskurin tyhjennys, rivinvaihtomerkin lukeminen erikseen

			switch (valinta) {
			case 1:
				System.out.println("Tuotteen valinta-toimintoa ei vielä toteutettu.");
				break;
			case 2:
				System.out.println("Tuotteen lisäys-toimintoa ei vielä toteutettu.");
				break;
			case 3:
				System.out.println("Tuotteen poisto-toiminto ei vielä toteutettu.");
				break;
			case 0:
				System.out.println("Kiitos ja näkemiin!");
				break;
			default:
				System.out.println("Virheellinen valinta. Valitse uudelleen!");
				break;
			}

		} while (valinta != 0);
		input.close();
	}
	
	

	

}
