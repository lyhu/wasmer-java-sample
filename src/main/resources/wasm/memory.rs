#[no_mangle]
pub extern fn return_hello() -> *const u8 {
    b"Hello, World!\0".as_ptr()
}
