package UserPack;

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Đọc, viết, chỉnh sửa thẳng vào file XML
public class userReadWrite {

	private final String userFile = "user.xml";
	
	// Doc file XML va tra ve Document
	private Document getDoc() {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
		    builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Error: "+e);
		}
		
		Document document = null;
		try {
			File file = new File(userFile);
		    document = builder.parse(file);
		} catch (SAXException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error read file: "+e);
		}
		return document;
	}
	
	// Cap nhat file XML
	private void updateFile(File file, Document doc) {
		try {
			TransformerFactory transFactor = TransformerFactory.newInstance();
			Transformer tran = transFactor.newTransformer();
			// Chinh kieu output
			tran.setOutputProperty(OutputKeys.INDENT, "yes");
			tran.setOutputProperty(OutputKeys.METHOD, "xml");
			DOMSource source = new DOMSource(doc);
			StreamResult res = new StreamResult(file);
			tran.transform(source, res);
			
		} catch (Exception e) {
			System.out.println("Error update file: "+e);
		}
	}
	
	// Kiem tra tai khoan da ton tai chua
	boolean isExist(String email) {
		ArrayList<User> list = ReadUserList();
		for(User U : list) {
			if(U.getEmail().equals(email))
				return true;
		}
		return false;
	}
	
	// Tim 1 user trong danh sach va tra ve (LOGIN)
	User getUser(String email, String pas) {
		ArrayList<User> list = ReadUserList();
		for(User U : list) {
			if(U.getEmail().equals(email) && U.getPassword().equals(pas))
				return U;
		}
		return null;
	}
	
	// Them mot user vao file (SIGN UP)
	boolean insertUser(Member mem) {
		ArrayList<User> userList = ReadUserList();
		// Neu email da ton tai, return false
		if(isExist(mem.getEmail()))
			return false;
		String s = userList.get(0).getId().substring(3);
		int count = Integer.parseInt(s) + 1;
		String Id = "MEM" + count; 
		while(count < 9999) {
			boolean had = false;
			for(User U : userList) {
				if(U.getId().equals(Id))
					had = true;
			}
			if(!had) break;
			else {
				count++;
				Id = "MEM" + count;
			}
		}
		try {
			Document doc = getDoc();
			Node users = doc.getFirstChild();
			
			// Tao node user voi cac element
			Node newUser = doc.createElement("User");
			((Element)newUser).setAttribute("type", mem.getType());
			((Element)newUser).setAttribute("id", Id);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(mem.getName()));
			newUser.appendChild(name);
			
			Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode(mem.getFirstName()));
			newUser.appendChild(firstname);
			
			Element email = doc.createElement("email");
			email.appendChild(doc.createTextNode(mem.getEmail()));
			newUser.appendChild(email);
			
			Element pass = doc.createElement("password");
			pass.appendChild(doc.createTextNode(mem.getPassword()));
			newUser.appendChild(pass);
			
			users.appendChild(newUser);
			
			updateFile(new File(userFile), doc);
			
		} catch (Exception e) {
			System.out.println("Error add user: "+e);
		}
		return true;
	}
	
	// Cap nhat thong tin cua mot user (ten, ho)
	void updateUserInfo(User user) {
		try {
			Document doc = getDoc();
			
			// Tao node user voi cac element
			NodeList nodeListUser = doc.getElementsByTagName("User");
            for (int i = 0; i < nodeListUser.getLength(); i++) {
                Node nNode = nodeListUser.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                	Element U = (Element) nNode;
                	
                	// Tim ra user can cap nhat trong danh sach
                	String id = U.getAttribute("id");
                	if(id.equals(user.getId())) {
                		Element name = (Element) U.getElementsByTagName("name").item(0);
            			name.setTextContent(user.getName());
            			
            			Element firstname = (Element) U.getElementsByTagName("firstname").item(0);
            			firstname.setTextContent(user.getFirstName());
            			
            			break;
                	}
                }
            }
			updateFile(new File(userFile), doc);
			
		} catch (Exception e) {
			System.out.println("Error update user: "+e);
		}
	}
	
	// Cap nhat password cua mot user
	boolean updateUserPassword(String email, String password) {
		try {
			Document doc = getDoc();
			
			// Tao node user voi cac element
			NodeList nodeListUser = doc.getElementsByTagName("User");
            for (int i = 0; i < nodeListUser.getLength(); i++) {
                Node nNode = nodeListUser.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                	Element U = (Element) nNode;
                	
                	// Tim ra user can cap nhat trong danh sach
                	String mail = U.getElementsByTagName("email").item(0).getTextContent();
                	
                	if(mail.equals(email)) {
                		Element newPass = (Element) U.getElementsByTagName("password").item(0);
            			newPass.setTextContent(password);
                	}
                }
            }
			updateFile(new File(userFile), doc);
   			return true;
		} catch (Exception e) {
			System.out.println("Error update user: "+e);
			return false;
		}
	}
	
	// Xoa mot user
	boolean deleteUser(User user) {
		boolean hadDeleted = false;
		// Neu user là admin, khong duoc phep xoa
		if(user.getType().equals("admin"))
			return hadDeleted;
		try {
			Document doc = getDoc();
			Node users = doc.getFirstChild();
			// Tao node user voi cac element
			NodeList nodeListUser = doc.getElementsByTagName("User");
            for (int i = 0; i < nodeListUser.getLength(); i++) {
                Node nNode = nodeListUser.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                	Element U = (Element) nNode;
                	
                	// Tim ra user can cap nhat trong danh sach
                	String mail = U.getElementsByTagName("email").item(0).getTextContent();
                	if(mail.equals(user.getEmail())) {
                		users.removeChild(U);
                		hadDeleted = true;
                		break;
                	}
                }
            }
			updateFile(new File(userFile), doc);
			
		} catch (Exception e) {
			System.out.println("Error delete user: "+e);
		}
		return hadDeleted;
	}
	
	//---------Search for users--------------------------
	
	ArrayList<User> searchByKeyword(String key) {
		ArrayList<User> list = new ArrayList<>();
		try {
			ArrayList<User> users = ReadUserList();
			for(User U : users) {
				if(U.getName().toLowerCase().contains(key) || U.getEmail().contains(key) || U.getId().toLowerCase().contains(key))
					list.add(U);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	//------------------------------------------------------
	
	// Lay danh sach cac thanh vien
	ArrayList<User> ReadUserList() {
		Document doc = getDoc();
		ArrayList<User> list = new ArrayList<>();
        try {
        	doc.getDocumentElement().normalize();
        	 
            // Đọc tất cả các phần tử có tên thẻ là "user"
            NodeList nodeListUser = doc.getElementsByTagName("User");
            for (int i = 0; i < nodeListUser.getLength(); i++) {
                Node nNode = nodeListUser.item(i);
                User user = null;
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if(eElement.getAttribute("type").equals("admin"))
                    	user = new Admin();
                    else
                    	user = new Member();
                    user.setId(eElement.getAttribute("id"));
                   	user.setFirstName(eElement.getElementsByTagName("firstname").item(0).getTextContent());
                   	user.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                   	user.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
                  	user.setPassword(eElement.getElementsByTagName("password").item(0).getTextContent());
                }
                // Add đối tượng user vào list users
                list.add(user);
            }
        } catch (Exception e) {
			System.out.println("Error read users list: "+e);
		}
        return list;
	}
	
}
