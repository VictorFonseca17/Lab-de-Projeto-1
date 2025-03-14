package com.sistema_matricula.sistema_matricula.Enum;

public enum  Role {
    ALUNO(1),
    PROFESSOR(2),
    SECRETARIA(3);

        private final int value;

        Role(int valor) {
        this.value = valor;
    }

    public static Role getFromValue(int valor) {
        for (Role role : Role.values()) {
            if (role.value == valor) {
                return role;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }
}
