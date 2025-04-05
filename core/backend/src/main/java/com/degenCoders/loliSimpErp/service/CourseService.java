@Service
public class CourseService {
    @Autowired
    private CourseRepository repo;

    public List<Course> getAll() { return repo.findAll(); }
    public Course getByCode(String code) { return repo.findByCourseCode(code); }
}
