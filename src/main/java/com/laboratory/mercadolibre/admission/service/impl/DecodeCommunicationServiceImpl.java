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
        for (var i = 0; i < messages.size(); i++) {
            final String[] mainMessage = messages.get(i);
            var j = i + 1;
            while (j < messages.size()) {
                final String[] currentMessage = messages.get(j);
                boolean match = joinWords(mainMessage, currentMessage);
                if (match)
                    messages.removeIf(m -> Arrays.compare(m, currentMessage) == 0);
                else
                    j++;
            }
        }
        return String.join(" ", messages.get(0));
    }

    private boolean joinWords(String[] s1, String[] s2) {
        if (s1.length == s2.length) {
            return joinWordsEqualsWords(s1, s2);
        } else {
            var newS2 = new String[s1.length];
            Arrays.fill(newS2, "");

            var index2 = 0;
            while (s2[index2].isEmpty()) ++index2;

            var index1 = 0;
            while (s1[index1].compareToIgnoreCase(s2[index2]) != 0) ++index1;

            for (var i = index2; i < s2.length; i++)
                newS2[index1++] = s2[i];
            return joinWords(s1, newS2);
        }
    }

    private boolean joinWordsEqualsWords(String[] s1, String[] s2) {
        for (var i = 0; i < s1.length; i++) {
            if (s2[i].isEmpty())
                continue;
            if (!s1[i].isEmpty() && s1[i].compareToIgnoreCase(s2[i]) != 0)
                return false;
        }
        for (var i = 0; i < s1.length; i++)
            if (s1[i].isEmpty())
                s1[i] = s2[i];
        return true;
    }

    private int countWords(String[] s1, String[] s2) {
        return Long.compare(Arrays.stream(s2).filter(f -> !f.isEmpty()).count(),
                Arrays.stream(s1).filter(f -> !f.isEmpty()).count());
    }

}
