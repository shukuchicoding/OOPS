package objects;

import java.awt.image.BufferedImage;

public class GameObjs {
	public int x; //hoành độ của vị trí
	public int y; //tung độ của vị trí
	
	public String direction; //chọn hướng di chuyển: nhảy lên, cúi xuống, bắn đạn
	
	public BufferedImage up, down, run1, run2, attack1, attack2, death;
	
	int spriteCounter = 0; //đếm thời gian xuất hiện hình ảnh nhân vật
	int spriteNum = 1; //hình ảnh đầu tiên, ví dụ: run1, attack1		
	}
}
