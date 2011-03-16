package pacman.world.maps;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class TypeMap {
	
	private Type[][] typeMap;
	private int height;
	private int width;
	
	public TypeMap(File mapFile) throws IOException {
		determineMapDimension(mapFile);
		typeMap = new Type[width][height];
		parse(mapFile);
	}
	
	public Type[][] getTypeMap() {
		return typeMap;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void determineMapDimension(File mapFile) throws IOException {
		FileReader fileReader = new FileReader(mapFile);
		int x = 0;
		int y = 0;
		int character;
		main: while ((character = fileReader.read()) != -1) {
			if(character == '/') {
				while ((character = fileReader.read()) != 10)
					if(character == -1)
						break main;
			} else if(character == '\n') {
				if (y > 0 && x != width) {
					System.err.println("Error in input file: map is not rectangular.");
					System.exit(1);
				}
				width = x;
				x = 0;
				y++;
			} else {
				x++;
			}
		}
		fileReader.close();
		height = y+1;
	}
	
	private void parse(File mapFile) throws IOException {
		FileReader fileReader = new FileReader(mapFile);
		int x = 0;
		int y = 0;
		int character;
		main: while ((character = fileReader.read()) != -1) {
			if(character == '/') {
				while ((character = fileReader.read()) != 10)
					if(character == -1)
						break main;
			} else if(character == '\n') {
				x = 0;
				y++;
			} else {
				Type type = Type.get(character);
				if(type == null) {
					System.err.println("Error in input file: unexpected character '"+character+"'.");
					System.exit(1);
				}
				typeMap[x][y] = type;
				x++;
			}
		}
		fileReader.close();
	}
}
