package com.rcvalladao.blockchainauctionserver.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.4.
 */
@SuppressWarnings("rawtypes")
public class Auction extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620008e4380380620008e4833981016040819052620000349162000264565b81518051839160009162000050918391602090910190620000b5565b5060208281015180516200006b9260018501920190620000b5565b50604091909101516002909101805460ff90921660ff19909216919091179055600380546001600160a01b03191633179055620000a9814262000327565b600655506200038b9050565b828054620000c3906200034e565b90600052602060002090601f016020900481019282620000e7576000855562000132565b82601f106200010257805160ff191683800117855562000132565b8280016001018555821562000132579182015b828111156200013257825182559160200191906001019062000115565b506200014092915062000144565b5090565b5b8082111562000140576000815560010162000145565b634e487b7160e01b600052604160045260246000fd5b604051606081016001600160401b03811182821017156200019657620001966200015b565b60405290565b604051601f8201601f191681016001600160401b0381118282101715620001c757620001c76200015b565b604052919050565b600082601f830112620001e157600080fd5b81516001600160401b03811115620001fd57620001fd6200015b565b602062000213601f8301601f191682016200019c565b82815285828487010111156200022857600080fd5b60005b83811015620002485785810183015182820184015282016200022b565b838111156200025a5760008385840101525b5095945050505050565b600080604083850312156200027857600080fd5b82516001600160401b03808211156200029057600080fd5b9084019060608287031215620002a557600080fd5b620002af62000171565b825182811115620002bf57600080fd5b620002cd88828601620001cf565b825250602083015182811115620002e357600080fd5b620002f188828601620001cf565b6020830152506040830151925060ff831683146200030e57600080fd5b6040810192909252506020939093015192949293505050565b600082198211156200034957634e487b7160e01b600052601160045260246000fd5b500190565b600181811c908216806200036357607f821691505b602082108114156200038557634e487b7160e01b600052602260045260246000fd5b50919050565b610549806200039b6000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063430ca46f146100515780637ae42ff91461005b5780638e7ea5b2146100795780639979ef4514610099575b600080fd5b6100596100ac565b005b61006361017a565b604051610070919061046c565b60405180910390f35b6100816102db565b6040516001600160a01b039091168152602001610070565b6100596100a73660046104bf565b610340565b6003546001600160a01b031633146101195760405162461bcd60e51b815260206004820152602560248201527f4f6e6c7920746865206f776e65722063616e2066696e6973682074686520617560448201526431ba34b7b760d91b60648201526084015b60405180910390fd5b60065442101561016b5760405162461bcd60e51b815260206004820181905260248201527f41756374696f6e20706572696f6420686173206e6f7420656e646564207965746044820152606401610110565b6007805460ff19166001179055565b6040805160608082018352808252602082015260009181019190915260006040518060600160405290816000820180546101b3906104d8565b80601f01602080910402602001604051908101604052809291908181526020018280546101df906104d8565b801561022c5780601f106102015761010080835404028352916020019161022c565b820191906000526020600020905b81548152906001019060200180831161020f57829003601f168201915b50505050508152602001600182018054610245906104d8565b80601f0160208091040260200160405190810160405280929190818152602001828054610271906104d8565b80156102be5780601f10610293576101008083540402835291602001916102be565b820191906000526020600020905b8154815290600101906020018083116102a157829003601f168201915b50505091835250506002919091015460ff16602090910152919050565b60075460009060ff166103305760405162461bcd60e51b815260206004820152601960248201527f41756374696f6e20686173206e6f7420656e64656420796574000000000000006044820152606401610110565b506004546001600160a01b031690565b60075460ff16156103935760405162461bcd60e51b815260206004820152601960248201527f41756374696f6e2068617320616c726561647920656e646564000000000000006044820152606401610110565b600081116103ef5760405162461bcd60e51b815260206004820152602360248201527f4269642076616c7565206d7573742062652067726561746572207468616e207a60448201526265726f60e81b6064820152608401610110565b6005548110806103ff5750600554155b1561041c576005819055600480546001600160a01b031916331790555b50565b6000815180845260005b8181101561044557602081850181015186830182015201610429565b81811115610457576000602083870101525b50601f01601f19169290920160200192915050565b602081526000825160606020840152610488608084018261041f565b90506020840151601f198483030160408501526104a5828261041f565b91505060ff60408501511660608401528091505092915050565b6000602082840312156104d157600080fd5b5035919050565b600181811c908216806104ec57607f821691505b6020821081141561050d57634e487b7160e01b600052602260045260246000fd5b5091905056fea2646970667358221220d0f049e13049dfc917cf4128f32807f4cc4cebd3e8be755930f3269638a979c564736f6c63430008090033";

    public static final String FUNC_FINISHAUCTION = "finishAuction";

    public static final String FUNC_GETREQUIREMENTS = "getRequirements";

    public static final String FUNC_GETWINNER = "getWinner";

    public static final String FUNC_PLACEBID = "placeBid";

    @Deprecated
    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> finishAuction() {
        final Function function = new Function(
                FUNC_FINISHAUCTION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Requirements> getRequirements() {
        final Function function = new Function(FUNC_GETREQUIREMENTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Requirements>() {}));
        return executeRemoteCallSingleValueReturn(function, Requirements.class);
    }

    public RemoteFunctionCall<String> getWinner() {
        final Function function = new Function(FUNC_GETWINNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> placeBid(BigInteger value) {
        final Function function = new Function(
                FUNC_PLACEBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Auction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Auction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class Requirements extends DynamicStruct {
        public String vnfName;

        public String vnfType;

        public BigInteger numCpus;

        public Requirements(String vnfName, String vnfType, BigInteger numCpus) {
            super(new org.web3j.abi.datatypes.Utf8String(vnfName), 
                    new org.web3j.abi.datatypes.Utf8String(vnfType), 
                    new org.web3j.abi.datatypes.generated.Uint8(numCpus));
            this.vnfName = vnfName;
            this.vnfType = vnfType;
            this.numCpus = numCpus;
        }

        public Requirements(Utf8String vnfName, Utf8String vnfType, Uint8 numCpus) {
            super(vnfName, vnfType, numCpus);
            this.vnfName = vnfName.getValue();
            this.vnfType = vnfType.getValue();
            this.numCpus = numCpus.getValue();
        }
    }
}
