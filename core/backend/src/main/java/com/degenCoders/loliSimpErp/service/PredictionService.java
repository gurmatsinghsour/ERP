@Service
public class PredictionService {
    @Autowired
    private PredictionRepository repo;

    public List<Prediction> getByStudentId(int id) { return repo.findByStudentId(id); }
    public Prediction save(Prediction p) { return repo.save(p); }
}
