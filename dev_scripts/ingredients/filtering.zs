import crafttweaker.api.ingredient.type.IIngredientAny;
val allLogs = IIngredientAny.getInstance().onlyIf("is_logs", stack => {
    val path = stack.registryName.path;

    if "stripped" in path {
        if path.endsWith("log") || path.endsWith("hypae") || path.endsWith("stem") || path.endsWith("block") {
            return stack.definition in <tag:item:c:stripped_logs>;
        }
    }
    return false;
});

for item in game.items {
    if allLogs.matches(item) {
        <tag:item:c:stripped_logs>.add(item);
    }
}