package com.laboratory.mercadolibre.admission.service.impl;

import com.laboratory.mercadolibre.admission.service.IDecodeCommunicationService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DecodeCommunicationServiceImpl implements IDecodeCommunicationService {
    @Override
    public String decodeMessage(List<String[]> messages) {
        messages = messages.stream().sorted(this::countWords).collect(Collectors.toList());
        for (int i = 0; i < messages.size(); i++) {
            for (int j = i + 1; j < messages.size(); j++) {
                boolean match = joinWords(messages.get(i), messages.get(j));
                if (match) {
                    messages.remove(j);
                    j = i;
                }
            }
        }
        return String.join(" ", messages.get(0));
    }

    private boolean joinWords(String[] s1, String[] s2) {
        if (s1.length == s2.length) {
            for (int i = 0; i < s1.length; i++) {
                if (s2[i].isEmpty())
                    continue;
                if (!s1[i].isEmpty() && s1[i].compareToIgnoreCase(s2[i]) != 0)
                    return false;
            }
            for (int i = 0; i < s1.length; i++)
                if (s1[i].isEmpty())
                    s1[i] = s2[i];
            return true;
        } else {
            String[] newS2 = new String[s1.length];
            Arrays.fill(newS2, "");

            int index2 = 0;
            while (s2[index2].isEmpty()) ++index2;

            for (int i = index2; i < s2.length; i++)
                newS2[i] = s2[i];
            return joinWords(s1, newS2);
        }
    }

    private int countWords(String[] s1, String[] s2) {
        return Long.compare(Arrays.stream(s2).filter(f -> !f.isEmpty()).count(),
                Arrays.stream(s1).filter(f -> !f.isEmpty()).count());
    }

}
