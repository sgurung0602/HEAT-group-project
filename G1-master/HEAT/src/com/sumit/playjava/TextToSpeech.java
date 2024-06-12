package com.sumit.playjava;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.ElementIterator;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import java.util.Scanner;
import javax.swing.SwingWorker;
import javax.swing.text.Document;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {

	private static final String PROMPT = "Prelude>";
	private int lastScannedPosition = 0;
	private Voice voice;
	private volatile boolean continueSpeaking = true;
	
	public void performTextToSpeech(String text) {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            
            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice voice = voiceManager.getVoice("kevin16");
            
            if (voice != null) {
                voice.allocate();
                voice.speak(text);
                voice.deallocate();
            } else {
                System.out.println("Error: Could not allocate voice.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public void readConsoleText(JTextPane consolePane) {
	    Document doc = consolePane.getDocument();

	    try {
	        int start = 0;
	        int end = doc.getLength();
	        final String consoleText = doc.getText(start, end - start);

	        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	            @Override
	            protected Void doInBackground() throws Exception {
	                performTextToSpeech(consoleText);
	                return null;
	            }
	        };

	        worker.execute();
	    } catch (BadLocationException e) {
	        e.printStackTrace();
	    }
	}
    
    public void readConsoleLine(JTextPane consolePane) {
    	StyledDocument styledDoc = (StyledDocument) consolePane.getDocument();
        ElementIterator iterator = new ElementIterator(styledDoc);
        Element element;

        try {
            int start = lastScannedPosition;
            int docLength = styledDoc.getLength();

            while (start < docLength && (element = iterator.next()) != null) {
                int end = Math.min(element.getEndOffset(), docLength);
                String text = styledDoc.getText(start, end - start);

                // Check if the prompt is present in the text
                int promptIndex = text.lastIndexOf(PROMPT);
                if (promptIndex != -1) {
                    // Extract from the last prompt occurrence to the end
                    String relevantText = text.substring(promptIndex);

                    // Process the relevant text
                    performTextToSpeech(relevantText);
                }

                start = end;
            }

            // Update the last scanned position
            lastScannedPosition = start;

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    public void shutUp() {
        continueSpeaking = false;
    }
}    
