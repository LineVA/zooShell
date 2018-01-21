/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class KeeperAddTrainingContext {
    private final Zoo zoo;
    private final String keeperName;
    private final String familyName;
    
    private AnimalKeeper keeper;
    private Family family;
}
