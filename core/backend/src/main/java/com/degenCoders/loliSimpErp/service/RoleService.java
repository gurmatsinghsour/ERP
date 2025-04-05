@Service
public class RoleService {
    @Autowired
    private RoleRepository repo;

    public List<Role> getAll() { return repo.findAll(); }
    public Role getByName(String name) { return repo.findByRoleName(name); }
}
