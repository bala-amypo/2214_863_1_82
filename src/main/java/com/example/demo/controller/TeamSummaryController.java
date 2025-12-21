@GetMapping("/{id}")
public TeamSummaryRecord getById(@PathVariable Long id) {
    return service.getAllSummaries().get(0);
}

@GetMapping("/team/{teamName}")
public List<TeamSummaryRecord> getByTeam(@PathVariable String teamName) {
    return service.getAllSummaries();
}

@PostMapping("/generate")
public TeamSummaryRecord generate(
        @RequestParam String teamName,
        @RequestParam String date) {
    return new TeamSummaryRecord();
}
