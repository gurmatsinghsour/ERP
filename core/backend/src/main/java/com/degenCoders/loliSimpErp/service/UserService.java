@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> getAll() { return repo.findAll(); }
    public User getByUsername(String username) { return repo.findByUsername(username); }
    public User create(User user) { return repo.save(user); }
}
