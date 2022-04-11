
public class App {

	public static void main(String[] args) {
		//0b  -binary
		//0x  -hexadecimal
/* 
 * Bitwise Operations
 * -------------------
* 	AND	  &					NOT 	~
*    OR	  |				LEFT SHIFT	<<	
* 	XOR	  ^	   		   RIGHT SHIFT  >>
* 
* 
* 
*   AND (&) operates on boolean values the same way as &&
*   ---------------------------
*   Only true if both 			0 & 0 = 0
* 	input bits are true			1 & 0 = 0
* 								0 & 1 = 0
* 								1 & 1 = 1
* 
* 
* 	OR
* 	-----------------------------
* 	True if any input bit 		0 | 0 = 0
* 	is true   					1 | 0 = 1
* 								0 | 1 = 1
* 								1 | 1 = 1
* 
* 	XOR  (Exclusive or) 
* 	-----------------------------
* 	True if one and only 		0 ^ 0 = 0
* 	one input bit is true		1 ^ 0 = 1
* 								0 ^ 1 = 1
* 								1 ^ 1 = 0
* 
* 	NOT
* 	-----------------------------
* 	Ones' complement operator	~0 = 1
* 	Flips the input bit			~1 = 0
* 
* 
* 	LEFT SHIFT
* 	-----------------------------
* 	Shift the binary digits				00010110		2+4+16=22
* 	by n, pad 0's on the right		   <<				(22 * 2 * 2 = 88)
* 	Each shift is a multiply by 2		00000010   	->(this tells us how many places to shift, for this case it is 2)
* 	(unless there's overflow)			01011000		8+16+64=88
* 
* 	
* 	RIGHT SHIFT
* 	-----------------------------
* 	Shift the binary digits				00010110		2+4+16=22
* 	by n, pad 0's on the left		   >>				(22 / 2 / 2 = 5)
* 	Each shift is a divide by 2			00000010   	->(this tells us how many places to shift, for this case it is 2)
* 	with round towards					00000101		1+4=5
* 	negative infinity		
* 

**********BIT TRICKS************
* 
* 	CHECK IF EVEN
* 	-------------
* 						0110	=6
* 	(x & 1) == 0		&
* 						0001
* 						----
* 						0000	=>even
* 
* 
* 	CHECK IF POWER OF TWO
* 	---------------------
* 	any number that's a power of two
* 	will have only one binary digit set
*	because it's a binary number system
*	
* 						x: 		1000	=8
* 						x-1: 	0111	=7
* 
* 						1000
* 	(x & x-1) == 0		&		
* 						0111
* 						----
* 						0000	=>true
* 
*/						

		
	System.out.println("Set bit: " + Integer.toBinaryString(setBit(0b00000110, 0b00000101)));  //00100110
	System.out.println("Clear bit: " + Integer.toBinaryString(clearBit(0b00000110, 0b00000010)));  //00000010
	System.out.println("Flip bit: " + Integer.toBinaryString(flipBit(0b01100110, 0b00000010)));  //01100010
	System.out.println("Is bit set (1-true, 0-false): " + Integer.toBinaryString(isBitSet(0b01100110, 0b00000101)));  //1
	System.out.println("Modify bit with state 1: " + Integer.toBinaryString(modifyBit(0b00000110, 0b00000101, 0b00000001))); //00100110
	System.out.println("Modify bit with state 0: " + Integer.toBinaryString(modifyBit(0b00000110, 0b00000010, 0b00000000))); //00000010		
		
	System.out.println("Compare difference: " + compareDiff(0b00000111, 0b11100000));	
	}
	
	
	public static int setBit(int x, int position) {
		// here we have x binary digits, and we want to set one of those bits to true
		// we have bunch of 0s and we want to set one of those to 1.
		// x - input string 
		// position - the position that we want to set to true so which column
		// in the input string should be a 1
		// First, we should create a mask
		int mask = 1 << position;
		return x | mask;
		
/*		   x	00000110
 * 	position	00000101  (set to this position which is 5)
 *      mask	00100000  (we constructed the mask that has the bit set in the 5th position)
 *      		76543210  (position will go by with index number)
 *     
 *     	   x	00000110
 *     				|
 *      mask	00100000	
 *      result:	00100110   =38			
 *     
 */
	}
	
	public static int clearBit(int x, int position) {
		int mask = 1 << position;
		return x & ~mask;
		
/*		   x	00000110
 * 	position	00000010  (set to this position which is 2)
 *      mask	00000100  (we constructed the mask that has the bit set in the 2nd position)
 *     ~mask	11111011
 *     ------------------
 *     	   x	00000110
 *     				&
 *     ~mask	11111011	
 *      result:	00000010   =2			
 *     
 */

	}
	
	
	public static int flipBit(int x, int position) {
		int mask = 1 << position;
		return x ^ mask;
		
/*		   x	01100110
 * 	position	00000010  (set to this position which is 2)
 *      mask	00000100  (we constructed the mask that has the bit set in the 2nd position)
 *     ------------------
 *     
 *     	   x	01100110
 *     				^
 *      mask	00000100	
 *      result:	01100010   =98			
 *     
 */

	}
	
	
	public static int isBitSet(int x, int position) {
		int shifted = x >> position;
		return shifted & 1;
		
/*		   x	01100110
 * 	position	00000101 
 *   shifted	00000011
 *   -------------------    
 *   shifted	00000011
 *     				&
 *     	   1	00000001	
 *      result:	00000001   =1 which is true		
 *     
*/		
	}


	public static int modifyBit(int x, int position, int state) {
		//state 1 is setting a bit
		//state 0 is clearing a bit
		int mask = 1 << position;
		return (x & ~mask) | (-state & mask);

/*
WITH STATE 1:
 * x:  		  00000110   ~mask: 	    11011111     00000110  x & ~mask
 * position:  00000101	 -state: 	    11111111	 	|
 * state:  	  00000001	 x & ~mask:     00000110     00100000  -state & mask
 * mask: 	  00100000	 -state & mask: 00100000     -----------------------
 *													 00100110  = result (successfully set the bit in the index number 5)
 *
WITH STATE 0:
 * x:  		  00000110   ~mask: 	    11111011     00000010  x & ~mask
 * position:  00000010	 -state: 	    00000000	 	|
 * state:  	  00000000	 x & ~mask:     00000010     00000000  -state & mask
 * mask: 	  00000100	 -state & mask: 00000000     -----------------------
 *													 00000010  = result (successfully cleared out the bit in the index number 2)
 */
	}
	
	
	public static int compareDiff(int num1, int num2) {
		//count the number of bits that are different bet two numbers
		
//		//option 1:
//		int diff = num1 ^ num2;
//		return Integer.bitCount(diff);
//		
//		
//		
//		//option 2: 
//		int count = 0;
//		int diff = num1 ^ num2;
//		while (diff>0) {
//			count++;
//			diff = diff & (diff-1);
//		}
//		return count;
		
		
		
		//option 3:
		int count = 0;
		int diff = num1 ^ num2;
		while (diff>0) {
			if (diff % 2 == 1)
				count++;
			diff = diff / 2;
			
		}
		return count;
			
	}
	

}
