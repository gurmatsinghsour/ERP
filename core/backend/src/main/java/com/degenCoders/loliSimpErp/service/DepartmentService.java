@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repo;

    public List<Department> getAll() { return repo.findAll(); }
    public Optional<Department> getById(int id) { return repo.findById(id); }
    public Department create(Department d) { return repo.save(d); }
    public void delete(int id) { repo.deleteById(id); }
}
