package com.baijia123.reflect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteralPetCreator extends PetCreator {
    
    public static final List<Class<? extends Pet>> allTypes = Collections.unmodifiableList(
            Arrays.asList(Pet.class, Dog.class, Cat.class,Mutt.class, EgyptianMau.class));
    
    private static final List<Class<? extends Pet>> types = allTypes.subList(allTypes.indexOf(Mutt.class), allTypes.size());

    @Override
    public List<Class<? extends Pet>> getTypes() {
        // TODO Auto-generated method stub
        return types;
    }

}
