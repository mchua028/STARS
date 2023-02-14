//https://rosettacode.org/wiki/Substitution_cipher#Java
//using substitution cipher to encrypt and decrypt the password

public class PasswordSecurity{
	
	private final String key = "]kYV}(!7P$n5_0i R:?jOWtF/=-pe'AD&@r6%ZXs\"v*N"
         + "[#wSl9zq2^+g;LoB`aGh{3.HIu4fbK)mU8|dMET><,Qc\\C1yxJ";
	 
	public String encrypt(String s) {
     StringBuilder sb = new StringBuilder(s.length());

     for (char c : s.toCharArray())
         sb.append(key.charAt((int) c - 32));
     
     return sb.toString();
 }
	
	public String decrypt(String s) {
     StringBuilder sb = new StringBuilder(s.length());

     for (char c : s.toCharArray())
         sb.append((char) (key.indexOf((int) c) + 32));

     return sb.toString();
 }
	
}
