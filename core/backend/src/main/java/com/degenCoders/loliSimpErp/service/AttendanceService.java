@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository repo;

    public List<Attendance> getByStudentId(int id) { return repo.findByStudentId(id); }
    public Attendance save(Attendance att) { return repo.save(att); }
}
