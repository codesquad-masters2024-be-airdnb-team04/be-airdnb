<script>
    // import { auth } from "../../stores/auth.js";
    export let registerToggle

    const data = {
        memberId: '',
        password: ''
    }

    // 회원가입 버튼 제어
    let password_first = ''
    $: password_first

    let password_second = ''
    $: password_second

    let isSubmitLock = true
    $: isSubmitLock = data.memberId.trim() === '' || password_first.trim() === '' || password_second.trim() === ''

    function onRegisterMode() {
        registerToggle = false
    }

    const resetData = () => {
        data.memberId = ''
        data.password = ''
        password_first = ''
        password_second = ''
    }

    const onRegister = async () => {
        if(password_first !== password_second){
            alert("비밀번호가 일치하지 않습니다. 다시 입력해 주세요!")
        } else{
            data.password = password_first
            await auth.register(data)
            resetData()
        }
    }

    const onEnterKey = (e) => {
        if (e.key === 'Enter') {
            onRegister();
        }
    }
</script>


<div class="login-form-input-container">
    <span class="login-form-label">아이디</span>
    <input name="memberId" bind:value={data.memberId} class="login-input-field" type="text" required
           autocomplete="username" placeholder="아이디를 입력하세요" maxlength="20"/>
</div>
<div class="login-form-input-container">
    <span class="login-form-label">비밀번호</span>
    <input name="password" bind:value={password_first} class="login-input-field" type="password" required
           autocomplete="new-password" placeholder="비밀번호를 입력하세요" maxlength="20"/>
</div>
<div class="login-form-input-container">
    <span class="login-form-label">비밀번호 확인</span>
    <input name="password" bind:value={password_second} class="login-input-field" type="password" required
           on:keyup={(e) => {onEnterKey(e)}} autocomplete="new-password" placeholder="같은 비밀번호를 입력하세요" maxlength="20"/>

</div>
<button id="register" type="submit" class="signup-btn" disabled={isSubmitLock} on:click={onRegister}>
    회원가입
</button>

<button type="button" class="back-btn" on:click={onRegisterMode}>
    <span class="mr-1 text-[16px]">
        <i class="bi bi-arrow-left-square"></i>
    </span>
    로그인 화면으로 돌아가기
</button>

