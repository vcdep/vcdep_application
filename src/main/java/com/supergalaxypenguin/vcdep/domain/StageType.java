/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supergalaxypenguin.vcdep.domain;

/**
 * Enums that are used to determine what stage is being represented
 * @author Howtoon
 */
public enum StageType {

    /**
     * The checkout stage is used to get the code from github
     */
    CHECKOUT,

    /**
     * The static analysis stage is used to check for syntax errors
     */
    STATIC,

    /**
     * the unit test stage runs the unit tests of the project
     */
    UNIT,

    /**
     * the integration stage checks the project against its other modules
     */
    INTEGRATION,

    /**
     * the build stage checks to make sure the project builds successfully
     */
    BUILD,

    /**
     * the deploy stage exports the project to its final destination
     */
    DEPLOY
}
