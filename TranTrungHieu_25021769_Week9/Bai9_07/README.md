# Bài 9.07 — Automated Code Review via Pull Request

## Mục tiêu

Biến CI pipeline thành một "người review" tự động: khi có Pull Request hướng vào `main`, GitHub Actions sẽ tự chạy Checkstyle, đăng inline comments vào các dòng vi phạm, và Branch Protection Rules sẽ chặn nút Merge cho đến khi tất cả lỗi được sửa.

## Hướng tiếp cận

| Yêu cầu | Giải pháp |
| --- | --- |
| **Trigger** | Workflow `.github/workflows/ci-bai9-07.yml` lắng nghe `pull_request` vào `main`, dùng `paths:` filter để chỉ chạy khi PR đụng tới `Bai9_07/`. |
| **Inline Feedback** | Dùng `dbelyaev/action-checkstyle@v1` với `reporter: github-pr-review` — bot sẽ comment **trực tiếp** vào đúng dòng code vi phạm thay vì chỉ in log. |
| **Branch Protection** | Cấu hình BP Rules trên `main`: required status check `Checkstyle Review` + require PR before merging → nút Merge bị disable khi job fail. |
| **Verification** | Tạo PR test với lỗi Checkstyle cố ý → bot comment + Merge bị khóa. Sau khi sửa lỗi → Merge mở lại. Bằng chứng xem trong `screenshots/`. |

### Vì sao dùng `dbelyaev/action-checkstyle` thay vì gọi Checkstyle CLI thẳng?

Action này wrap quanh `reviewdog`, hỗ trợ sẵn reporter `github-pr-review` để post inline comments. Nếu tự chạy `checkstyle.jar` trong workflow, ta sẽ phải tự parse XML output và gọi GitHub Review API — phức tạp hơn nhiều mà kết quả y hệt.

### Vì sao `fail_level: any`?

Để workflow trả về exit code khác 0 khi có bất kỳ lỗi nào → status check `Checkstyle Review` chuyển sang đỏ → Branch Protection chặn Merge. Đây là điểm quan trọng để yêu cầu "Branch Protection" hoạt động.

## Cấu trúc

```
Bai9_07/
├── src/
│   └── Main.java          # Java demo, đã pass checkstyle
├── checkstyle.xml         # Rule config
├── run.sh                 # Compile & chạy Main
├── README.md              # File này
└── screenshots/           # Bằng chứng verification
```

Workflow YAML đặt ở **root repo**: `.github/workflows/ci-bai9-07.yml` (theo convention các bài Bai9_XX đã làm trước đó).

## Cách chạy

### Chạy local (kiểm tra Java compile)

```bash
chmod +x run.sh
./run.sh
```

### Kiểm tra CI/CD trên GitHub

1. Đảm bảo workflow `ci-bai9-07.yml` đã được commit lên `.github/workflows/` ở root repo.
2. Cấu hình **Branch Protection Rules** cho nhánh `main`:
   - Settings → Branches → Add rule → Branch name pattern: `main`
   - ✅ Require a pull request before merging
   - ✅ Require status checks to pass before merging → tick `Checkstyle Review`
   - ✅ Require conversation resolution before merging
   - ✅ Do not allow bypassing the above settings
3. Tạo branch test và PR có lỗi cố ý → quan sát:
   - Bot post inline comments vào các dòng vi phạm.
   - Status check `Checkstyle Review` đỏ.
   - Nút **Merge pull request** bị disable.

## Verification

Xem các ảnh trong `screenshots/`:

- `01-pr-with-violations.png` — PR có lỗi: bot comment inline + Merge bị khóa.
- `02-branch-protection-rules.png` — Cấu hình BP Rules trên `main`.
- `03-pr-passed.png` — Sau khi fix lỗi: status check xanh, nút Merge mở lại.

Link PR test: _(điền URL PR thật của bạn vào đây sau khi tạo)_.
