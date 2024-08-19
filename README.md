springboot learn

1.@ControllerAdvice @RestControllerAdvice 自定义全局异常处理

2.spring security 权限控制  
@PreAuthorize @PostAuthorize  方法调用前或者调用后进行权限检查

@PreFilter @PostFilter        对集合类型的参数或者返回值进行过滤

3.JWT token 身份认证

4.文件上传 下载   excel导入 导出

5.自定义注解 权限校验 日志处理 统计等
@Before @After @Around @AfterReturning @AfterThrowing

6.自定义配置信息
@ConfigurationProperties  @EnableConfigurationProperties

7.@Profile @Value

8.@PathVariable注解

9.@EnableAsync 启用异步注解    @Async注解
AsyncConfigurer

ThreadPoolExecutor ThreadPoolTaskExecutor
java.util.concurrent
hutool cn.hutool.core.thread
guava  com.google.common.util.concurrent

springboot常用注解
@SpringBootApplication
@Component、@Service、@Controller、@Repository
@ResponseBody
@RestController
@AutoWired、@Qualifier、@Resource
@RequestMapping、@GetMapping、@PostMapping
@Value、@ConfigurationProperties、@PropertySource
@Configuration、@Bean
@RequestParam、@RequestBody、@PathVariable、@RequestHeader、@CookieValue


API接口的安全措施：
使用HTTPS  使用OAuth2认证 使用API网关
接口验证
使用WebAuthn
API进行加密签名
API版本控制   get/v1/user/123
授权 流控
只允许请求的接口
使用工具检测漏洞
正确的错误处理


