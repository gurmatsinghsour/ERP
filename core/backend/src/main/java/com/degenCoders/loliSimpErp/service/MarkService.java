@Service
public class MarkService {
    @Autowired
    private MarkRepository repo;

    public List<Mark> getByStudentId(int id) { return repo.findByStudentId(id); }
    public Mark save(Mark mark) { return repo.save(mark); }
}
