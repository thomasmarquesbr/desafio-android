package com.thomas.archtecture_framework.usecase

class InvalidUseCaseCall(customMessage: String) :
    Exception("YOU SHALL NOT PASS!!!\nThis method could not be called. $customMessage")
