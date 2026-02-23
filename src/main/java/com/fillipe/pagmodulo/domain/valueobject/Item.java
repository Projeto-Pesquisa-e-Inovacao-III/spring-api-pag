package com.fillipe.pagmodulo.domain.valueobject;

public record Item(
        Long id,
        String name,
        String description,
        Integer quantity,
        Integer unitAmount,
        String imageUrl
) {
    public Item {
        validateName(name);
        validateDescription(description);
        validateQuantity(quantity);
        validateUnitAmount(unitAmount);
        validateImageUrl(imageUrl);
    }

    public Item(Long id, String name, String description, Integer quantity, Integer unitAmount) {
        this(id, name, description, quantity, unitAmount, null);
    }

    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("O nome do produto deve ser não nulo e não vazio");
        if (name.length() > 100)
            throw new IllegalArgumentException("O nome do produto deve ter no máximo 100 caracteres");
    }

    private static void validateDescription(String description) {
        if (description != null && description.length() > 255)
            throw new IllegalArgumentException("A descrição do produto deve ter no máximo 255 caracteres");
    }

    private static void validateQuantity(Integer quantity) {
        if (quantity == null || quantity < 1 || quantity > 999)
            throw new IllegalArgumentException("Quantidade de um item deve ser não nula, maior que 1 e menor ou igual que 999");
    }

    private static void validateUnitAmount(Integer unitAmount) {
        if (unitAmount == null || unitAmount < 0 || unitAmount > 999_999_900)
            throw new IllegalArgumentException("O valor de um item deve ser não nulo, no mínimo 0 e menor ou igual que 999999900");
    }

    private static void validateImageUrl(String imageUrl) {
        if (imageUrl != null && imageUrl.trim().isEmpty())
            throw new IllegalArgumentException("A URL da imagem não pode ser vazia se fornecida");
    }
}

