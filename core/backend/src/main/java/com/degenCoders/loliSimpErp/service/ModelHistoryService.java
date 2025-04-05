@Service
public class ModelHistoryService {
    @Autowired
    private ModelHistoryRepository repo;

    public ModelHistory getByVersion(String version) { return repo.findByVersionName(version); }
    public List<ModelHistory> getAll() { return repo.findAll(); }
}
