package data;

public class Attendance {
	int id;
	String userId;
	String moimId;
	int status;

	String userName;
	String userAvatarURL;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMoimId() {
		return moimId;
	}

	public void setMoimId(String moimId) {
		this.moimId = moimId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAvatarURL() {
		return userAvatarURL;
	}

	public void setUserAvatarURL(String userAvatarURL) {
		this.userAvatarURL = userAvatarURL;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", userId=" + userId + ", moimId=" + moimId + ", status=" + status
				+ ", userName=" + userName + ", userAvatarURL=" + userAvatarURL + "]";
	}

}
