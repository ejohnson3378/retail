import com.company.retail

@RestController
class RootController {
    @RequestMapping('/')
    String indes() {
        return 'Hello from the retail application!'
    }
}