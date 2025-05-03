import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FibonacciResultRepository extends JpaRepository<FibonacciResult, Long> {
}