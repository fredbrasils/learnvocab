package br.com.learnvocab.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ReadCSV {
    
    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
    private Map<String,Integer> mapAlfabeto = new HashMap<>();
    
    public static void main(String[] args) {
         
        ReadCSV read = new ReadCSV();
        read.start();
    }
     
     public void start() {

        String csvFile = "src\\main\\resources\\englishwords.csv";
        String line = "";
        String cvsSplitBy = "\\s";
        List<Language> languages = new ArrayList<Language>();
        Map<String,Integer> equalsWords = new HashMap<>();
                
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            
            String beforeWord = "";
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] splitLine = line.split(cvsSplitBy);
                int speakPriority = 99;
                int writePriority = 99;
                
                if(!beforeWord.equals(splitLine[0])){ // verifica se a palavra anterior é igual (como a lista está ordenada por nome isso funciona)
                    
                    beforeWord = splitLine[0];
                    
                    for(String word : splitLine){

                        if(word.contains("S")){
                            speakPriority = Integer.parseInt(String.valueOf(word.trim().charAt(1)));
                        }else if(word.contains("W")){
                            writePriority = Integer.parseInt(String.valueOf(word.trim().charAt(1)));
                        }                    
                    }

                    String letra = String.valueOf(splitLine[0].toLowerCase().charAt(0));
                    Integer totalLetra = 1;
                    if(mapAlfabeto.containsKey(letra)){
                        totalLetra = mapAlfabeto.get(letra) + 1;
                    }

                    mapAlfabeto.put(letra, totalLetra);

                    languages.add(new Language(splitLine[0], speakPriority, writePriority));                
                }else {
                    Integer totalLetra = 1;
                    if(equalsWords.containsKey(beforeWord)){
                        totalLetra = equalsWords.get(beforeWord) + 1;
                    }
                    equalsWords.put(beforeWord, totalLetra);
                }
            }
            
            Collections.sort(languages);
            
            int total = languages.size();
            int group = 1;
            int countWord = 1;            
            
            System.out.println("Group " + group);
                         
            while(!languages.isEmpty()){
                
                for(char c : ALFABETO.toCharArray()){                    
                    
                    for(int i=0; i<languages.size();i++){
                    
                        if(languages.get(i).word.toLowerCase().charAt(0) == c){
                            System.out.println(countWord+" - " + languages.get(i).word);                            
                            countWord++;
                            languages.remove(i);
                            break;
                        }
                    }
                    
                    if(countWord == 21){ // se quiser mudar a quantidade de palavras por grupo é so alterar essa linha
                                         // colocar a quantidade que quer no grupo + 1
                        countWord = 1;
                        group++;                        
                        System.out.println("\nGroup " + group);
                    }
                }                
            }
            
            System.out.println("Total: " + total);
            System.out.println("Letra iguais: " + equalsWords);
            System.out.println("Qnt iguais: " + equalsWords.size());
            System.out.println("Qnt por letra: " + mapAlfabeto);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
     
    public class Language implements Comparable<Language>{
        
        private final String word;
        private final int speakPriority;
        private final int writePriority;

        public Language(String word, int speakPriority, int writePriority) {
            this.word = word;
            this.speakPriority = speakPriority;
            this.writePriority = writePriority;
        }

        @Override
        public int compareTo(Language language) {
            
            if (this.speakPriority < language.speakPriority) {
                return -1;
            }else if (this.speakPriority > language.speakPriority) {
                return 1;
            }
            
            if (this.writePriority < language.writePriority) {
                return -1;
            }else if (this.writePriority > language.writePriority) {
                return 1;
            }
            
            return 0;
        }
        
        @Override
        public String toString() {
            return word + " - S" +speakPriority+ " - W"+writePriority;
        }
        
   }
}
