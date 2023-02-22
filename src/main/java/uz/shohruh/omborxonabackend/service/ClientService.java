package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Client;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.ClientDTO;
import uz.shohruh.omborxonabackend.repository.ClientRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ApiResponse addClient(ClientDTO clientDTO){
        if (clientRepository.existsByName(clientDTO.getName()))
            return new ApiResponse("Bunday nomli client mavjud", false);
        Client client = new Client(
                clientDTO.getName(),
                clientDTO.getPhoneNumber());
        clientRepository.save(client);
        return new ApiResponse("Muavvaqiyatli saqlandi", true);
    }

    public ApiResponse editClient(ClientDTO clientDTO, Long id){
        if (!clientRepository.existsById(id))
            return new ApiResponse("Bunday client mavjud emas", false);
        Optional<Client> optional = clientRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Bunday client mavjud emas", false);
        Client editClient = optional.get();
        editClient.setName(clientDTO.getName());
        editClient.setPhoneNumber(clientDTO.getPhoneNumber());
        clientRepository.save(editClient);
        return new ApiResponse("Muavvaqiyatli taxrirlandi", true);
    }

    public ApiResponse deleteClient(Long id){
        if (!clientRepository.existsById(id))
            return new ApiResponse("Client mavjud emas", false);
        clientRepository.deleteById(id);
        return new ApiResponse("Muvaqqiyatli o'chirildi", true);
    }

    public ApiResponse getById(Long id){
        Optional<Client> optional = clientRepository.findById(id);
        return optional.map(client ->
                        new ApiResponse("Client", true, client))
                .orElseGet(() ->
                        new ApiResponse("Bunday clientlar mavjud emas", false));
    }

    public ApiResponse getAll(){
        List<Client> clientList = clientRepository.findAll();
        if (!clientList.isEmpty())
            return new ApiResponse("Barcha clientlar birliklar", true, clientList);
        return new ApiResponse("Clientlar mavjud emas", false);
    }

}
