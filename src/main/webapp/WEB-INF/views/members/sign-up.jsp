<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <%@ include file="../include/static-head.jsp" %>
    <link rel="stylesheet" href="/assets/css/sign-up.css">
  </head>

  <body>
    <%@ include file="../include/header.jsp" %>

    <div class="container wrap">
      <div class="row">
        <div class="offset-md-2 col-md-4">
          <div class="card">
            <div class="card-header text-white">
              <h2><span class="text-gray">Join the Blog Wave!</h2>
            </div>
            <div class="card-body">
              <form
                action="/members/sign-up"
                name="signup"
                id="signUpForm"
                method="post"
              >
                <table class="table">
                  <tr>
                    <td class="text-left">
                      <p>
                        <strong>Put your ID</strong
                        >&nbsp;&nbsp;&nbsp;
                        <span id="idChk"></span>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="text"
                        name="account"
                        id="user_id"
                        class="form-control tooltipstered"
                        maxlength="14"
                        required="required"
                        aria-required="true"
                        placeholder="Up to 14 chars, letters and numbers only."
                      />
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">
                      <p>
                        <strong>Put your password</strong
                        >&nbsp;&nbsp;&nbsp;<span id="pwChk"></span>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="password"
                        size="17"
                        maxlength="20"
                        id="password"
                        name="password"
                        class="form-control tooltipstered"
                        required="required"
                        aria-required="true"
                        placeholder="Password: 8+ chars, letters, numbers, special."
                      />
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">
                      <p>
                        <strong>Re-enter password to confirm.</strong
                        >&nbsp;&nbsp;&nbsp;<span id="pwChk2"></span>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="password"
                        size="17"
                        maxlength="20"
                        id="password_check"
                        name="pw_check"
                        class="form-control tooltipstered"
                        required="required"
                        aria-required="true"
                        placeholder="Passwords must match."
                      />
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">
                      <p>
                        <strong>Put your name</strong
                        >&nbsp;&nbsp;&nbsp;<span id="nameChk"></span>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="text"
                        name="name"
                        id="user_name"
                        class="form-control tooltipstered"
                        maxlength="6"
                        required="required"
                        aria-required="true"
                        placeholder="Up to 6 letters only in Korean."
                      />
                    </td>
                  </tr>
                  <tr>
                    <td class="text-left">
                      <p>
                        <strong>Put your E-mail address</strong
                        >&nbsp;&nbsp;&nbsp;<span id="emailChk"></span>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="email"
                        name="email"
                        id="user_email"
                        class="form-control tooltipstered"
                        required="required"
                        aria-required="true"
                        placeholder="ex) abc@mvc.com"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td class="text-center">
                      <p>
                        <strong
                          ></strong
                        >
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td class="text-center" colspan="2">
                      <input
                        type="submit"
                        value="Welcome you join your blog."
                        class="btn form-control tooltipstered"
                        id="signup-btn"
                      />
                    </td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script
      type="module"
      src="/assets/js/signUp.js"
    ></script>
  </body>
</html>
